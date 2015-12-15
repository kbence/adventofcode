#!/usr/bin/env python

if __name__ == '__main__':
    with open('./data.txt') as f:
        data = f.readline()
        print sum([-1 if b == ')' else 1 for b in data])