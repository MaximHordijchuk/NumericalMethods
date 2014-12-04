#!/bin/sh
cd res;
for f in *.int; do
echo "set term png \nset grid \nset key below \nset title \"${f%.*}\" \nset output \"${f%.*}.png\" \nplot \"$f\" smooth unique lc rgb 'blue' title '${f%.*}', \"points.pts\" with points lc rgb 'red' title 'points'" | gnuplot;
done;
