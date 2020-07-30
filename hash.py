# programmers hash problems

"""
 1. 완주하지 못한 선수 찾기
    input: participant, completion
      participant: 마라톤에 참여한 선수들의 이름이 담긴 배열
      completion: 완주한 선수들의 이름이 담긴 배열
    output: 완주하지 못한 선수의 이름
    
    예시) participant = ["Kim", "Lee", "Park"]
         completion = ["Lee", "Park"]   
         output = "Kim"
"""

def find_player(participant, completion):
    # dictionary 선언은 {}로 한다.
    players = {}
    # dictionary key는 array index와 달리 숫자일 필요가 없다.
    for player in participant:
        if player not in players.keys():
            players[player] = 1
        else:
            players[player] += 1
            
    for player in completion:
        if players[player] > 0:
            players[player] -= 1
            
    answer = "".join([x for x, y in players.items() if players[x] > 0])
    return answer

"""
2. 스파이가 옷을 입는 방법
   input: clothes
     clothes: 스파이가 가진 의상들이 담긴 2차원 배열
   output: 서로 다른 옷의 조합의 수   
   
   예시) clothes = [["yellow_hat", "headgear"],
                   ["blue_sunglasses", "eyewear"],
                   ["green_turban", "headgear"]]   
        output = 5
"""

from functools import reduce

def spy_wear(clothes):
    clothesMap = {}
    for name, feature in clothes:
        if feature in clothesMap:
            clothesMap[feature] += 1
        else:
            clothesMap[feature] = 2
    return reduce((lambda x, y: x * y), clothesMap.values()) - 1

"""
3. 베스트 앨범 만들기
   input: genres, plays
     genres: 노래 장르를 나타내는 배열
     plays: 노래별 재생 횟수를 나타내는 배열
   output: 베스트 앨범에 들어갈 노래의 고유번호를 담은 리스트

   베스트 앨범에 들어가는 기준:
    1. 속한 노래가 많이 재생된 장르를 먼저 수록한다.   
    2. 장르 내에서 많이 재생된 노래를 먼저 수록한다.   
    3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록한다.
"""

from functools import reduce

def makeGenreList(genres):
    genreMap = {}
    for i, genre in enumerate(genres):
        if genre in genreMap:
            genreMap[genre].append(i)
        else:
            genreMap[genre] = [i]
    return genreMap

def sortByGenre(genreMap, plays):
    countMap = {}
    for genre in genreMap.keys():
        playcount = [plays[idx] for idx in genreMap[genre]]
        countMap[genre] = reduce((lambda x, y: x + y), playcount)
    countList = list(countMap.items())
    countList.sort(key=lambda x: x[1], reverse=True)
    return [x[0] for x in countList]
        
def sortInGenre(genreMap, genreList, plays):
    output = []
    for genre in genreList:
        count = [(idx, plays[idx]) for idx in genreMap[genre]]
        count.sort(key=lambda x: (-x[1], x[0]))
        output += [x[0] for x in count][:2]
    return output

def makeAlbum(genres, plays):
    genreMap = makeGenreList(genres)
    genreList = sortByGenre(genreMap, plays)
    answer = sortInGenre(genreMap, genreList, plays)
    return answer

"""
4. 전화번호 목록
    input: phone_book
        phone_book: 전화번호를 담은 배열
    output: 어떤 번호가 다른 번호의 접두어인 경우면 false, 그렇지 않으면 true

    예시) phone_book = ["123", "456", "789"]
         output = true

         phone_book = ["119", "1193742874", "57042834"]
         output = false
"""

def findPrefixNum(phone_book):
    phone_book.sort()
    for i, number in enumerate(phone_book):
        for j, tmp in enumerate(phone_book):
            if i >= j:
                continue
            elif tmp.startswith(number):
                return False
            else:
                continue
    return True