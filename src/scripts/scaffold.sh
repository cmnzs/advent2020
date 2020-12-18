BASE_DIR="/Users/cmenezes/journee/advent2020"

fcreate () {
  n=$1
  INPUT_URL="https://adventofcode.com/2020/day/$n/input"
  SRC_DIR="$BASE_DIR/src/main/kotlin/day$n"
  RESOURCE_DIR="$BASE_DIR/src/main/resources/day$n"

  echo "$n"
  echo "$SRC_DIR"
  echo "$RESOURCE_DIR"
  echo $INPUT_URL

  mkdir "$SRC_DIR"
  touch "$SRC_DIR/day$n.kt"
  echo "package day$n
import files.loadFile

fun main() {
    val f = loadFile(\"day$n/day$n.txt\")
}" >> "$SRC_DIR/day$n.kt"

  mkdir "$RESOURCE_DIR"
  curl --request GET -sL \
       --url "$INPUT_URL"\
       -H "$AUTH_COOKIE" \
       --output "$RESOURCE_DIR/day$n.txt"

}

fcreate $1

