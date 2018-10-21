#!/usr/bin/env python3

import os
import shutil
import sys
from os.path import dirname


IGNORED = [
    'build',
    '.git',
    '.idea',
    '.gradle',
    'scripts',
    'mad-lab',
    'README.md',
    'start_experiment.sh'
    'android_testspace.iml'
]

SRC_FILES = [
    'java',
    'kt',
    'gradle'
]

ORIGINAL_PACKAGE_NAME = 'com.testspace'
PROJECT_DIR = os.path.abspath(os.path.abspath(__file__)+'/../../..')


class Fork:
    fork_package_dir = ''
    package_name = ''
    original_package_dir = ORIGINAL_PACKAGE_NAME.replace('.', '/')

    def run(self, fork_dir, package_name):
        self.package_name = package_name
        self.fork_package_dir = package_name.replace('.', '/')
        shutil.copytree(PROJECT_DIR, fork_dir,
                        copy_function=self.copy_and_mutate_package,
                        ignore=self.get_ignored)

        os.remove(fork_dir+'/settings.gradle')
        with open(fork_dir+'/settings.gradle', 'w') as f:
            f.write('include ":app"')

        replace_in(fork_dir+'/app/build.gradle', "implementation project(':MadLab')", '')
        pass

    def get_ignored(self, src, names):
        for ignored_element in IGNORED:
            if ignored_element in names:
                names.remove(ignored_element)

        for ignored_element in IGNORED:
            if ignored_element in src:
                return names

        return []

    def copy_and_mutate_package(self, src, dst):
        if self.original_package_dir in dst:
            dst = dst.replace(self.original_package_dir, self.fork_package_dir)
            path = dirname(dst)
            if not os.path.exists(path):
                os.makedirs(path)

        copy_result = shutil.copy(src, dst)
        if os.path.basename(dst).split(".")[-1] in SRC_FILES:
            replace_in(dst, ORIGINAL_PACKAGE_NAME, self.package_name)
        return copy_result


def replace_in(file, what, with_what):
    with open(file, 'r') as f:
        data = f.readlines()

    for i, line in enumerate(data):
        if what in line:
            data[i] = line.replace(what, with_what)

    with open(file, 'w') as f:
        f.writelines(data)


def run(package_name, fork_dir):
    Fork().run(fork_dir, package_name)


if __name__ == '__main__':
    if len(sys.argv) >= 1 + 2:
        run(sys.argv[1], sys.argv[2])
    else:
        print('Usage: \n'+
              'python3 fork.py org.fork.test /path/to/fork')
pass
