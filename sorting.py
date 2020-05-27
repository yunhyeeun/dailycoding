## programmers sorting problems

"""
 1. K번째 수
    input: array, commands
      array: 숫자 배열
      commands: [i, j, k]를 원소로 가진 2차원 배열
    output: array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수
    
    예시) array = [1, 5, 2, 6, 3, 7, 4]
         commands = [2, 5, 3], [4, 4, 1], [1, 7, 3]
         output = [5, 6 , 3]
"""

def find_k(array, commands):
    answer = []
    for start,end,order in commands :
        array_temp = sorted(array[start-1:end])
        answer.append(array_temp[order-1])
    return answer

"""
 2. 가장 큰 수
    input: numbers
      numbers:  0 또는 양의 정수가 담긴 배열
    output: 순서를 재배치하여 만들 수 있는 가장 큰 수의 문자열

    - numbers의 길이는 1 이상 100,000 이하
    - numbers의 원소는 0 이상 1,000 이하

    예시) numbers = [6, 10, 2]
         output = "6210"
"""

def find_max(numbers):
    answer = ''
    sorted_numbers = sorted([ str(num) for num in numbers ], key=lambda x: (x*4)[:4], reverse=True)
    answer = "".join(sorted_numbers)
    if answer.startswith("0"):
        answer = "0"
    return answer

"""
 3. H-Index
    input: citations
      citations: 과학자가 발표한 논문의 인용 횟수를 담은 배열
    output: H-Index

    - H-Index: 어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고
               나머지 논문이 h번 이하 인용되었을 때 h의 최댓값
"""

def find_h_index(citations):
    sorted_c = sorted(citations,reverse=True)
    for i in range(len(sorted_c)):
        if sorted_c[i] < i+1:
            return i
    return len(citations)
