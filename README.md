# html-tool

A small utility to manipulate (X)HTML files, based on [jsoup](https://jsoup.org/).

~~~
$ mvn clean verify
$ java -cp "target/html-tool.jar:target/dependency/*" com.github.phoswald.html.tool.HtmlTool

$ docker run -it --rm \
  -v $(pwd)/target:/target \
  -w /target \
  ghcr.io/graalvm/native-image:22.3.0 \
  -cp "html-tool.jar:dependency/*" com.github.phoswald.html.tool.HtmlTool html-tool
$ ./target/html-tool

$ html-tool normalize input.html output.html
$ html-tool normalize input.html output.html --generate-xml
~~~
