from solutions import p001, p121
from typing import List


def test_001():
    nums = [3, 2, 4]
    target = 6

    sol = p001.Solution()
    res = sol.twoSum(nums, target)
    assert [1, 2] == sorted(res)

def test_121():
    prices = [7,1,5,3,6,4]
    sol = p121.Solution()
    res = sol.maxProfit(prices)
    assert res == 5

    prices = [7,6,4,3,1]
    sol = p121.Solution()
    res = sol.maxProfit(prices)
    assert res == 0
