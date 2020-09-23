#! /bin/bash

git log --oneline --all --graph --decorate  $(git reflog | awk '{print $1}')

