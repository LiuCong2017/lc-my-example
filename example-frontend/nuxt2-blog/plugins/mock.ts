// @ts-ignore
import Mock from 'mockjs'

Mock.mock(/posts\.json/, {
  'list|1-10': [{
    'id|+1': 1,
    'title': 'my title'
  }]
})
