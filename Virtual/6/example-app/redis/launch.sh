#!/bin/sh

PASSWORD = $(cat /etc/redis-passwd/passwd)

if [[ "${HOSTNAME}" == "redis-0"]]; then
    redis-server --reqiurepass ${PASSWORD}
else 
    redis-server --slaveof redis-0.redis 6379 --masterauth ${PASSWORD}
    --reqiurepass ${PASSWORD}
fi