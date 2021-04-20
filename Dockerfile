FROM ubuntu

RUN apt update; apt-get install -y curl
RUN apt update; apt-get install -y gnupg

RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian /" |  tee -a /etc/apt/sources.list.d/sbt.list
RUN curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" |  apt-key add
RUN apt-get update; apt-get install -y sbt
