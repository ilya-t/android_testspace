#!/usr/bin/env python3

import os
import re
import shutil
from os.path import dirname

PROJECT_DIR = os.path.abspath(dirname(dirname(dirname(__file__))))

IGNORED = [
    'build',
    '.git',
    '.idea',
    '.gradle',
    'scripts',
]

ORIGINAL_PACKAGE_NAME = 'com.testspace'
ORIGINAL_PACKAGE_DIR = ORIGINAL_PACKAGE_NAME.replace('.', '/')


class Fork:
    fork_package_dir = ''

    def run(self, fork_dir, package_name):
        self.fork_package_dir = package_name.replace('.', '/')
        shutil.copytree(PROJECT_DIR, fork_dir,
                        copy_function=self.copy_and_mutate_package,
                        ignore=self.get_ignored)
        print(PROJECT_DIR)
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

        if ORIGINAL_PACKAGE_DIR in dst:
            dst = dst.replace(ORIGINAL_PACKAGE_DIR, self.fork_package_dir)
            path = dirname(dst)
            if not os.path.exists(path):
                os.makedirs(path)

        return shutil.copy(src, dst)

    def replace(self, file):
        with open(file, 'w') as f:
            f.write()
            f.write('file contents')

        with open ('input.txt', 'r' ) as f:
            content = f.read()
        content_new = re.sub('(\w{2})/(\d{2})/(\d{4})', r'\1-\2-\3', content, flags = re.M)

def run(fork_dir, package_name):
    Fork().run(fork_dir, package_name)
