#!/bin/bash
if [ "$TRAVIS_REPO_SLUG" == "radium226/example-travis" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk7" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

echo -e "Publishing javadoc...\n"

  cp -R target/apidocs $HOME/javadoc

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=gh-pages https://${GH_PAGES}@github.com/radium226/example-travis gh-pages

  cd gh-pages
  git rm -rf ./javadoc
  cp -Rf $HOME/javadoc ./javadoc
  git add -f .
  git commit -m "Lastest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages

  echo -e "Published Javadoc to gh-pages.\n"
  
fi

