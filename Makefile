all: clean test build

build:
	./gradlew build
.PHONY: build

clean:
	./gradlew clean
.PHONY: clean

test:
	./gradlew test
.PHONY: test
