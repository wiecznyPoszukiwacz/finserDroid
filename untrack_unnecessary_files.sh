#!/bin/bash

# Po prostu wykonaj ten skrypt
# chmod +x untrack_unnecessary_files.sh; ./untrack_unnecessary_files.sh

git rm --cached bin/pl/netcoffee/android/finserDroid/*.class;
git rm --cached bin/pl/netcoffee/android/finserDroid/FinserPlAPI.class;
git rm --cached bin/pl/netcoffee/android/finserDroid/ServiceProvider.class;
git rm --cached bin/resources.ap_;
git rm --cached default.properties;
