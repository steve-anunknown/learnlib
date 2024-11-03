#!/bin/bash

modeldot="$1"
model=$(basename "${modeldot%.*}")

echo "$modeldot"
sed 's/\[.*<tr>/ /' "$modeldot" |
    sed 's/<\/td><\/tr>.*$//' |
    sed 's/<\/td>.*<td>/ /' |
    sed 's/<td>//' |
    sed '/\[label.*\]/d' |
    grep -- "->" |
    sed 's/ -> / /' |
    sed 's/^ *//' > columns

mapfile -t orig_in < <(cut -d' ' -f3 columns | sort -u | uniq)
mapfile -t orig_out < <(cut -d' ' -f4 columns | sort -u | uniq)

mapfile -t inputs < <(echo "${!orig_in[@]}" | xargs -d' ' -I {} printf "I{}\n")
mapfile -t outputs < <(echo "${!orig_out[@]}" | xargs -d' ' -I {} printf "O{}\n")

for index in "${!orig_in[@]}"
do
    sed -i "s/ ${orig_in[$index]} / ${inputs[$index]} /" columns
done

for index in "${!orig_out[@]}"
do
    sed -i "s/ ${orig_out[$index]}$/ ${outputs[$index]}/" columns
done

# mapfile -t nodes < <(cut -d' ' -f1 columns | uniq)
# for node in "${nodes[@]}"
# do
#     echo "                .from(\"$node\")"
#     grep "^$node " columns > outgoing
#     while read -r from to in out
#     do
#         if [ "$to" = "$node" ]
#         then
#             echo "                .on(Input.$in).withOutput(Output.$out).loop()"
#         else
#             echo "                .on(Input.$in).withOutput(Output.$out).to(\"$to\")"
#         fi
#     done < outgoing | sed 's/^/\t/'
# done
# rm outgoing columns
