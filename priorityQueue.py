'''
1. 더 맵게
모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)

    input: scoville, K
      scoville: 음식의 스코빌 지수를 담은 배열
      K: 최소 스코빌 지수
    output: 모든 음식의 스코빌 지수를 K 이상을 만들기 위해 섞어야 하는 최소 횟수
'''

import heapq

def spicier(scoville, K):
    heapScoville = []
    for food in scoville:
        heapq.heappush(heapScoville, food)
    
    answer = 0
    while (heapScoville[0] < K):
        if len(heapScoville) <= 1:
            return -1
        first = heapq.heappop(heapScoville)
        second = heapq.heappop(heapScoville)
        heapq.heappush(heapScoville, first + second * 2)
        answer += 1
    return answer