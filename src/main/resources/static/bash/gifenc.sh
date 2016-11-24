#!/bin/sh

youDown=$(youtube-dl $1 -o $2 | grep -Po "$2([^\s]+)")
ffmpeg -y -ss 30 -t 3 -i $youDown -vf fps=3,scale=320:-1:flags=lanczos,palettegen /root/palette.png
gifDown=$(echo "$youDown" | sed -r 's/\.([^\s]+)/.gif/g')
ffmpeg -ss 30 -t 3 -i $youDown -i palette.png -filter_complex "fps=3,scale=320:-1:flags=lanczos[x];[x][1:v]paletteuse" $gifDown
