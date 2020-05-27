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

def spy_wear(clothes):
    closet = {}
    for c_name, c_type in clothes:
        if c_type in closet:
            closet[c_type] += 1
        else:
            # 안 입는 경우를 고려하여 2를 default로 한다
            closet[c_type] = 2
    answer = 1
    for num in closet.values():
        answer *= num
    # 하나도 안 입는 경우 제외
    answer -= 1
    return answer

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

def make_album(genres, plays):
    songs = {}
    playcount = {}
    # 장르별 총 재생횟수를 구하여 정렬
    for i in range(len(genres)):
        if genres[i] in playcount:
            songs[genres[i]].append((i, plays[i]))
            playcount[genres[i]] += plays[i]
        else:
            songs[genres[i]] = [(i, plays[i])]
            playcount[genres[i]] = plays[i]

    sorted_playcount = [ genre for genre, count in sorted(playcount.items(), key=lambda x: x[1], reverse=True) ]
    
    answer = []  
    # 장르 내 노래별 재생횟수 정렬
    for genre in sorted_playcount:
        songlist = songs[genre]
        sorted_songlist = sorted(songlist, key=lambda x: (-x[1], x[0]))

        for i in range(len(songlist)):
            if i > 1:
                break
            else:
                answer.append(sorted_songlist[i][0])
    return answer