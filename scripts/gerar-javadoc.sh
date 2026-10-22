#!/usr/bin/env sh
set -eu
cd "$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)"
javadoc -quiet -private -Xdoclint:all -Werror -notimestamp \
    -encoding UTF-8 -docencoding UTF-8 -charset UTF-8 \
    -author -version -windowtitle 'FinançApp | Documentação JavaDoc' \
    -doctitle 'FinançApp — Calculadora' \
    -d docs -sourcepath src/main/java -subpackages calculadora
