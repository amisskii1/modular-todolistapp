@echo off

set "url=https://api.github.com/users/AntonMisskii/packages/maven/com.misskii.javatodolistapp/versions"
set "api=Accept: application/vnd.github+json"
set "apiVersion=X-GitHub-Api-Version: 2022-11-28"
set "output=versions.json"

curl -L -H "%api%" -H "%token_packages%" -H "%apiVersion%" "%url%" -o "%output%"