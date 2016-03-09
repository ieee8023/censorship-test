# censorship-test

How to use:
```
$ sh run.sh alexa-top-5000.txt | tee  insideresults 
google.com,80,Success
google.co.in,80,Success
facebook.com,80,Success
google.de,80,Success
wikipedia.org,80,Success
youtube.com,80,Success
msn.com,80,Success
qidian.com,80,Success
namecheap.com,80,Success
porntube.com,80,UnknownHostException   <-- content blocked by DNS
makeuseof.com,80,Success
infolinks.com,80,Success
...
```
