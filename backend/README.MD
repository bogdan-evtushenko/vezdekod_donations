# example config:
```
{
  "port": 3000,
  "mongo": {
    "url": "mongodb://localhost:27017/donations",
    "dbName": "donations"
  }
}
```

# routes:
## main API POINT: http://161.35.21.226:3000/api/android/

* Route GET: http://161.35.21.226:3000/api/android/?userId= 1 
Без параметров получаем массив всех записей  
C параметров записи конкретного пользователя
response: 

```
[
    {
        _id: <string>,
        user_id: <number>,
        data: any
    }
]
```


* Route POST: http://161.35.21.226:3000/api/android/new
body:
```
{
    userId: <number>,
    data: any
}
```
response: 
```
{
    success: true | false,
    id: string
}
```
