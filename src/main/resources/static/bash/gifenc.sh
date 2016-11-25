#!/bin/sh

link=$l
fileName="/var/www/html/media/$f.mp4"
BASEDIR=$(dirname "$0")

cd $BASEDIR
youtube-dl -f 'bestvideo[ext=mp4]+bestaudio[ext=m4a]/mp4' --merge-output-format mp4 $link -o "$fileName"

if [ -f "$fileName" ]; then
#	/root/bin/ffmpeg -y -ss 30 -t 3 -i $fileName -vf fps=3,scale=320:-1:flags=lanczos,palettegen "$BASEDIR/palette.png"
	/root/bin/ffmpeg -y -t 3 -i $fileName -vf fps=3,scale=320:240:flags=lanczos,palettegen "$BASEDIR/palette.png"
	gifDown=$(echo "$fileName" | sed -r 's/\.([^\s]+)/.gif/g')
#	/root/bin/ffmpeg -ss 30 -t 3 -i $fileName -i palette.png -filter_complex "fps=3,scale=320:-1:flags=lanczos[x];[x][1:v]paletteuse" $gifDown
	/root/bin/ffmpeg -t 3 -i $fileName -i "$BASEDIR/palette.png" -filter_complex "fps=3,scale=320:240:flags=lanczos[x];[x][1:v]paletteuse" $gifDown
fi