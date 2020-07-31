# programmers stak&queue problem

'''
1. 탑
수평 직선에 탑 N대를 세웠습니다.
모든 탑의 꼭대기에는 신호를 송/수신하는 장치를 설치했습니다.
발사한 신호는 신호를 보낸 탑보다 높은 탑에서만 수신합니다.
또한, 한 번 수신된 신호는 다른 탑으로 송신되지 않습니다.
맨 왼쪽부터 순서대로 탑의 높이를 담은 배열 heights가 매개변수로 주어질 때 각 탑이 왼쪽으로 쏜 신호를 어느 탑에서 받았는지 기록한 배열을 return 하도록 solution 함수를 작성해주세요.
신호를 수신하는 탑이 없으면 0으로 표시합니다.

예시) heights = [6,9,5,7,4] output = [0,0,2,2,4]
             = [3,9,9,3,5,7,2] output = [0,0,0,3,3,3,6]
             = [1,5,3,6,7,6,5] output = [0,0,2,0,0,5,6]
'''
def tower(heights):
    answer = []
    for i in range(len(heights)-1, -1, -1):
        send = heights[i]
        for j in range(i-1, -1, -1):
            receive = heights[j]
            if receive > send:
                answer.append(j+1)
                break
        if len(answer) != len(heights) - i:
            answer.append(0)
    answer.reverse()
    return answer

'''
2. 다리를 지나는 트럭

트럭 여러 대가 강을 가로지르는 일 차선 다리를 정해진 순으로 건너려 합니다.
모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다.
트럭은 1초에 1만큼 움직이며, 다리 길이는 bridge_length이고 다리는 무게 weight까지 견딥니다.   
solution 함수의 매개변수로 다리 길이 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭별 무게 truck_weights가 주어집니다.
이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.
'''
def truck(bridge_length, weight, truck_weights):
    total = len(truck_weights)
    inBridge = []
    outBridge = []
    truck_weights.reverse()
    time = [0] * len(truck_weights)
    
    while (len(outBridge) < total):
        while len(truck_weights) > 0 and sum(inBridge)+ truck_weights[-1] <= weight:
            inBridge.append(truck_weights.pop())
            time[:len(inBridge) + len(outBridge)] = [x+1 for x in time[:len(inBridge) + len(outBridge)]]
        while (time[len(outBridge)] < bridge_length):
            time[:len(inBridge) + len(outBridge)] = [x+1 for x in time[:len(inBridge) + len(outBridge)]]
        outBridge.append(inBridge.pop(0))
    return time[0] + 1

'''
3. 기능개발

먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.   
progresses	 speeds     return   
[93,30,55]	[1,30,5]    [2,1]  
'''
from math import ceil

def findNum(completeDays):
    for i in range(1, len(completeDays)):
        if completeDays[i] > completeDays[0]:
            return i
    return len(completeDays)

def func(progresses, speeds):
    answer = []
    completeDays = [ceil((100-x)/y) for x, y in zip(progresses, speeds)]
    while (len(completeDays) > 0):
        endNum = findNum(completeDays)
        answer.append(endNum)
        completeDays = completeDays[endNum:]
    return answer

'''
4. 프린터

새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.
1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
3. 그렇지 않으면 J를 인쇄합니다.
현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때, 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.
'''

def printer(priorities, location):
    answer = 0
    index = list(range(0,len(priorities)))
    while len(priorities) > 0:
        if priorities[0] == max(priorities):
            tmp = priorities.pop(0)
            tmpidx = index.pop(0)
            answer += 1
            if tmpidx == location:
                break
        else:
            tmp = priorities.pop(0)
            priorities.append(tmp)
            tmpidx = index.pop(0)
            index.append(tmpidx)
    return answer

'''
5. 쇠막대기

레이저는 여는 괄호와 닫는 괄호의 인접한 쌍 '()'으로 표현합니다.
또한 모든 '()'는 반드시 레이저를 표현합니다.
쇠막대기의 왼쪽 끝은 여는 괄호 '('로, 오른쪽 끝은 닫힌 괄호 ')'로 표현됩니다.
쇠막대기와 레이저의 배치를 표현한 문자열 arrangement가 매개변수로 주어질 때, 잘린 쇠막대기 조각의 총 개수를 return 하도록 solution 함수를 작성해주세요.
'''

def laser(arrangement):
    laser = arrangement.replace("()", ".")
    print (laser)
    answer = 0
    stick = 0
    for e in laser:
        if e == "(":
            stick += 1
        elif e == ")":
            answer += 1
            stick -= 1
        else:
            answer += stick
    return answer

'''
6. 주식가격

초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.
'''

def stock(prices):
    answer = []
    for i, p in enumerate(prices):
        if i == len(prices)-1:
            answer.append(0)
            break
        for j in range(i+1, len(prices)):
            if prices[j] < p:
                answer.append(j-i)
                break
        if len(answer) == i:
            answer.append(len(prices)-i-1)
    return answer