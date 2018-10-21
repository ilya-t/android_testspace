import os
import shutil
import subprocess
import unittest

import fork

FORK_DIR = os.path.abspath(os.path.dirname(__file__)) + '/fork_test'
FORK_PACKAGE = 'org.fork.check'


class ForkTest(unittest.TestCase):

    def setUp(self):
        super().setUp()
        fork.run(fork_dir=FORK_DIR, package_name=FORK_PACKAGE)

    def test_folder_copied_correctly(self):
        self.assertTrue(os.path.exists(FORK_DIR))

    def test_gradlew_clean(self):
        self.run_cmd('./gradlew projects')

    def tearDown(self):
        super().tearDown()
        shutil.rmtree(FORK_DIR)

    def run_cmd(self, *commands):
        for command in commands:
            with subprocess.Popen(command,
                                  shell=True,
                                  stdout=subprocess.PIPE,
                                  stderr=subprocess.PIPE,
                                  universal_newlines=True,
                                  cwd=FORK_DIR) as p:
                retcode = p.wait()
                if retcode != 0:
                    for line in p.stdout.readlines():
                        print(line)
                    self.fail('failed to execute command:' + command)


if __name__ == '__main__':
    unittest.main()
