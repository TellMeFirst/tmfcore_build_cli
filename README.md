# TMFCoreBuildCli

This repository contains the subrepositories needed to build the
[core module](https://github.com/bassosimone/tmfcore) of
[TellMeFirst (TMF)](http://tellmefirst.polito.it/) as a fat
jar runnable from the command line.

## How to clone this repository

Since this repository contains submodules, to clone this you have to
run the following command:

    git clone --recursive https://github.com/bassosimone/tmfcore_build_cli

Otherwise, if you have already cloned, you can fetch the submodules using
the following command:

    git submodule update --init

## Dependencies

We use [maven](http://maven.apache.org/) to build and clean the code,
so you need to install it. This repository also assumes that you use
[Java8](https://jdk8.java.net/). So, you need to install Java8 as well.

On Xubuntu 14.10, you can install all the above packages by running
the following command:

    sudo apt-get install maven openjdk-8-jdk

## How to build and clean the code

Once you have installed all the dependencies, you can build the
code using the following command:

    mvn package

You can instead clean the compiled artifacts using:

    mvn clean

To combine both (recommended to be sure that the compile process
starts from scratch) do:

    mvn clean package

## How this repository is organized

The top-level
[pom.xml](https://github.com/bassosimone/tmfcore_build/blob/master/pom.xml)
file references two modules: `tmfcore`, and `tmfcore_cli`.

The `tmfcore` module (which is also a git submodule) contains the core
functionality of TMF, that is, the `classify()` API. More information on
this module can be found in the [related git
repository](https://github.com/bassosimone/tmfcore).

The `tmfcore_cli` module (which is not a git submodule for simplicity but
may become one in the future) contains the code needed to prepare a
fat jar runnable from the command line.

## How to test this code

Run the following command:

    $ java -jar tmfcore_cli/target/tmfcore_cli.jar -f file -l lang -n nTopics

Where `file` is the file to classify, `lang` is the language (i.e., `it`
or `en`) and `nTopics` is the maximum number of topics to return.

By default, the minimal index installed at `data` is used, but this can
be changed by editing `conf/server.properties`.

As a test `file` you can use, for example, `test/test_file.txt`.
