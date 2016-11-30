The ammonite scripts in this folder just break out the large script `morphology.sc` into smaller tasks.  It starts from a single, simple text file with one chunk of text per line, like the output of `collecttextscript.sc`.  You could prepare this data as follows:

1. `amm collecttextscript.sc Geneva49.xml` (this writes a couple of thousand files named `cell*.txt`)
2. `cat cell*txt > geneva49all.txt` (this pastes together all the `cell*.txt` files on to a single file, here named `geneva49all.txt`)


Then we can apply one or more of these scripts in sequence:

**1.** `distinctWords.sc` takes one file as its parameter, and spits out a list of unique words.  E.g., `amm distinctWords.sc geneva49all.txt > wordlist.txt` will then save the results in the file `wordlist.txt`
**2.**  `parse.sc` takes this wordlist as a parameter, sends off requests to Morpheus, and writes out the XML output to a separate file with two columns, word + analyes.  This is handy in case we need to rerun any of the later steps, but don't want to re-fetch over the internet from morpheus. E.g., `amm parse.sc wordlist.txt > analyses.tsv`
