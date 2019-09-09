## 카카오 페이 과제
## 지자체 협약 지원 API 개발

## 개발 프레임워크
- Java 1.8
- Spring 5
- Maven 3
- JPA, Hibernate, QueryDSL
- h2-database (인메모리 DB)
- maven-tomcat7

## 문제 해결방법 
### 엔티티구조   
- RegionEntity : RegionSupportEntity 를 1:1 구조로 설계
- RegionEntity : 기관 정보 (기관코드, 기관이름)
- RegionSupportEntity : 기관의 지원 협약 정보 (협약시퀀스, 기관코드, 지원한도, 대상, etc)

## 빌드 및 실행방법
```shell script
gimgyeongho$ git clone https://github.com/kimkoungho/kakaopay-government
```

- intellj 로 import
- 프로젝트 디렉토리로 이동
```shell script
gimgyeongho$ cd kakaopay-government
gimgyeongho$ pwd
/Users/gimgyeongho/Desktop/kakaopay-government
```
- mvn clean package tomcat7:run

### 요구사항
- 데이터 파일에서 레코드를 DB 에 저장하는 API
파일 업로드 이후 OpenCsv 라이브러리를 이용하여 csv 파싱후 모든 데이터 인메모리 DB 저장  
 
- 요청 
```shell script
curl --request POST \
   -F file=@"src/main/resources/data/data.csv" http://localhost:8080/api/region/v1/upload/save
```

- 응답
```json 
{
    "data":{
        "fileName":"data.csv",
        "savedCount":98
    },
    "apiExceptionList":[],
    "httpCode":200
}
```

- 지자체 목록 검색 API
모든 협약 정보 제공 

- 요청
```shell script
curl --request GET \
   http://localhost:8080/api/region/v1
```

- 응답
```json
{
    "data": [
        {
            "regionSupportSeq": 1,
            "recordSeq": 1,
            "regionCode": "reg_0000000000000001",
            "regionName": "강릉시",
            "target": "강릉시 소재 중소기업으로서 강릉시장이 추천한 자",
            "usage": "운전",
            "limitDesc": "추천금액 이내",
            "rateDesc": "3%",
            "institute": "강릉시",
            "management": "강릉지점",
            "reception": "강릉시 소재 영업점",
            "createDate": "2019-09-09T11:21:00",
            "updateDate": "2019-09-09T11:21:00"
        },
        {
            "regionSupportSeq": 2,
            "recordSeq": 2,
            "regionCode": "reg_0000000000000002",
            "regionName": "강원도",
            "target": "강원도 소재 중소기업으로서 강원도지사가 추천한 자",
            "usage": "운전",
            "limitDesc": "8억원 이내",
            "rateDesc": "3%~5%",
            "institute": "강원도",
            "management": "춘천지점",
            "reception": "강원도 소재 영업점",
            "createDate": "2019-09-09T11:21:00",
            "updateDate": "2019-09-09T11:21:00" 
        } // .. 생략 .. 
    ],
    "apiExceptionList": [],
    "httpCode": 200
}
```

- 지자체 정보 1개 조회 API
- 요청  
```shell script
curl --request GET \
   http://localhost:8080/api/region/v1/%ea%b0%95%ec%9b%90%eb%8f%84
```
- 응답
```json
{
    "data": {
        "regionSupportSeq": 2,
        "recordSeq": 2,
        "regionCode": "reg_0000000000000002",
        "regionName": "강원도",
        "target": "강원도 소재 중소기업으로서 강원도지사가 추천한 자",
        "usage": "운전",
        "limitDesc": "8억원 이내",
        "rateDesc": "3%~5%",
        "institute": "강원도",
        "management": "춘천지점",
        "reception": "강원도 소재 영업점",
        "createDate": "2019-09-09T11:21:00",
        "updateDate": "2019-09-09T11:21:00"
    },
    "apiExceptionList": [],
    "httpCode": 200
}
```

- 지자체 정보 수정 API
-  요청
```shell script
curl --request PUT \
   http://localhost:8080/api/region/v1/%ea%b0%95%ec%9b%90%eb%8f%84?target=test
```
- 응답
```json
{
    "data": {
        "regionSupportSeq": 2,
        "recordSeq": 2,
        "regionCode": "reg_0000000000000002",
        "regionName": "강원도",
        "target": "test",
        "usage": "운전",
        "limitDesc": "8억원 이내",
        "rateDesc": "3%~5%",
        "institute": "강원도",
        "management": "춘천지점",
        "reception": "강원도 소재 영업점",
        "createDate": "2019-09-09T11:21:00",
        "updateDate": "2019-09-09T11:28:01"
    },
    "apiExceptionList": [],
    "httpCode": 200
}
```

- 지원한도 컬럼에서 지원금액으로 내림차순 정렬 
    * 지원금액이 같으면, 이차보전 평균 비율이 적은순서 특정 개수만
    * K 개수 -> K 개 지자체명 (강릉시, 강원도)

- 요청
```shell script 
curl --request GET \
   http://localhost:8080/api/region/v1/limit/count/sort?limitCount=3
```

- 응답
```json
{
    "data": [
        "경기도",
        "제주도",
        "국토교통부"
    ],
    "apiExceptionList": [],
    "httpCode": 200
}
```


- 이차보전 컬럼에서 보전 비율이 가장 적은 추천 기관명 API

- 요청 
```shell script 
curl --request GET \
   http://localhost:8080/api/region/v1/min/rate
```

- 응답
```json
{
    "data": "경주시",
    "apiExceptionList": [],
    "httpCode": 200
}
```

- 지자체 정보 수정 API 에러 테스트 
- 요청
```shell script
curl --request PUT \
   http://localhost:8080/api/region/v1/regionName?target=test
                                           
```

- 응답
```json
{
    "data": null,
    "apiExceptionList": [
        {
            "exceptionCode": "NOT_FOUND_UPDATE_TARGET",
            "exceptionMessage": "target: com.kakaopay.jpa.entity.RegionEntity, key: regionName",
            "exception": "com.kakaopay.common.exceptioin.BadRequestException"
        }
    ],
    "httpCode": 400
}
```
