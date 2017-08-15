#!/bin/bash
EXPERIMENT_NAME=$1
CURRENT_EXPERIMENT=$(git rev-parse --abbrev-ref HEAD)

if [ "$EXPERIMENT_NAME" == "" ]; then
    read -p "Enter experiment name: " EXPERIMENT_NAME
fi

if [ "$EXPERIMENT_NAME" == "" ]; then
    echo "wrong experiment name!"
    exit 1
fi

if [[ $(git status --short) ]]; then
    if [ "$CURRENT_EXPERIMENT" == "master" ]; then
        echo "You are currently on master with changes. Please commit them manually and start expetiment!"
        git status --short
        exit 2
    fi
    echo "Auto-commiting changes:"
    git status --short
    git commit --all --message "auto-commit (due to new experiment: "$EXPERIMENT_NAME")"
fi

eval "git branch "$EXPERIMENT_NAME" master"
eval "git checkout "$EXPERIMENT_NAME
