# 라이브러리
import datetime
from bs4 import BeautifulSoup
from urllib.request import Request, urlopen

# 사이트 크롤링
req = Request('https://afk.guide/redemption-codes/', headers={'User-Agent' : 'Mozilla/5.0'})

# url open
page = urlopen(req).read()

# html로 긁어 와서, 쿠폰이 있는 부분 HTML 코드를 page에 저장
soup = BeautifulSoup(page, 'html.parser')
page = soup.select('#wps-box > div.wps-box-content > ul > li')

couponCode = []
for i in page : 
    A = i.text # 텍스트 부분 추출
    B = ""
    for j in A : # A에 공백을 만나면 멈추고, 공백이 아니면 문자열 만들기
        if j == " " : break
        else : B += j
    # 쿠폰 코드 추가
    couponCode.append(B)

print(couponCode)