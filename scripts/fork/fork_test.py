import os
import shutil
import unittest

import fork

FORK_DIR = os.path.abspath(os.path.dirname(__file__)) + '/fork_test'
FORK_PACKAGE = 'org.fork'


class ForkTest(unittest.TestCase):

    def setUp(self):
        super().setUp()
        fork.run(fork_dir=FORK_DIR, package_name=FORK_PACKAGE)

    def test_folder_copied_correctly(self):
        self.assertEqual("/Users/oneday/workspace/android_testspace/scripts/fork/fork_test", FORK_DIR)

    def tearDown(self):
        super().tearDown()
        os.system('tree ' + FORK_DIR + ' -L 2')
        shutil.rmtree(FORK_DIR)


if __name__ == '__main__':
    unittest.main()
