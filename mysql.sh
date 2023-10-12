#!/bin/zsh

 docker run -d -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -v ~/.volume/todo-list:/var/lib/mysql mysql:8
