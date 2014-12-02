#!/bin/sh
cd res;
for f in *.int; do
echo "set term png \nset output \"${f%.*}.png\" \nplot \"$f\" smooth csplines, \"points.pts\" with points" | gnuplot;
done;
