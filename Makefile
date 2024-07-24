GRADLE_ARGS ?= --build-cache --parallel

build:
	./gradlew build ${GRADLE_ARGS}
.PHONY: build

clean:
	./gradlew clean ${GRADLE_ARGS}
.PHONY: clean ${GRADLE_ARGS}

format:
	./gradlew formatKotlin ${GRADLE_ARGS}
.PHONY: format

lint:
	./gradlew lintKotlin detekt ${GRADLE_ARGS}
.PHONY: lint

report:
	./gradlew koverHtmlReport koverXmlReport ${GRADLE_ARGS}
.PHONY: report

test:
	./gradlew test ${GRADLE_ARGS}
.PHONY: test
