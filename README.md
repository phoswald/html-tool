# html-tool

    $ mvn clean verify
    $ java -cp "target/html-tool.jar:target/dependency/*" com.github.phoswald.html.tool.HtmlTool

    $ docker run -it --rm \
      -v $(pwd)/target:/target \
      -w /target \
      oracle/graalvm-ce:1.0.0-rc15 \
      native-image -cp "html-tool.jar:dependency/*" com.github.phoswald.html.tool.HtmlTool html-tool
    $ ./target/html-tool
