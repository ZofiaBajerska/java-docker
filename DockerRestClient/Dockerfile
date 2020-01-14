FROM alpine:3.11.2
ENV ADDRESS=172.19.0.1:8080/user/list
RUN apk update && apk upgrade && \
   apk add curl
CMD curl -s $ADDRESS
