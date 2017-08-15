import os
import subprocess
import sys

import git

REPO_PATH = os.path.abspath(__file__ + "/../../")
NEW_BRANCH_NAME = None
import commandline
from commandline import Question


def try_create_branch(name):
    repo = git.Repo(REPO_PATH)

    if len(repo.index.diff(None)) > 0:
        print repo.git.status()
        commandline.ask(ask_stash_commit)
    elif not (name is None):
        print repo.git.branch(name, 'master')
        print repo.git.checkout(name)


        print "You're now on branch: " + name
        pass


ask_branch_name = Question('Enter branch name or nothing to quit: ', _parse_answer=lambda b: try_create_branch(b))


def stash_or_commit(command):
    if command == 'c':
        repo = git.Repo(REPO_PATH)
        print repo.git.commit('--all','--message', 'auto commit')
    elif command == 's':
        repo = git.Repo(REPO_PATH)
        print repo.git.stash()
    elif command == 'q':
        exit(1)
    pass

    global NEW_BRANCH_NAME

    if (NEW_BRANCH_NAME is None):
        commandline.ask(ask_branch_name)
    else:
        try_create_branch(NEW_BRANCH_NAME)

ask_stash_commit = Question('Stash(s) or Commit(c) current changes? (s, c, q)',
                            _parse_answer=lambda b: stash_or_commit(b))


def main(_args):
    if not _args or not _args[0]:
        commandline.ask(ask_stash_commits)
    else:
        global NEW_BRANCH_NAME
        NEW_BRANCH_NAME = _args[0]
        try_create_branch(NEW_BRANCH_NAME)
    pass


if __name__ == '__main__':
    args = sys.argv[1:] if len(sys.argv) > 1 else None
    main(args)
