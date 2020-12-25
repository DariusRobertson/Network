from bs4 import BeautifulSoup
import requests
import csv

source = requests.get('http://coreyms.com').text

soup = BeautifulSoup(source, 'lxml')

for article in soup.find_all('article'):

    headline = article.h2.a.text
    print(headline)

    summary = article.find('div', class_='entry-content').p.text
    print(summary)
    try:
        video_source = article.find('iframe', class_='youtube-player')['src']

        vidId = video_source.split('/')[4]
        vidId = vidId.split('?')[0]

        youtubeLink = 'https://youtube.com/watch?v={vidId}'.format(vidId=vidId)
    except Exception as e:
        youtubeLink = None

    print(youtubeLink)

    print(" ")
