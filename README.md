# HeendyFoody

![logo_footer1.png](https://github.com/sisi397/HeendyFoody/blob/main/WebContent/static/images/common/logo_footer1.png?raw=true)

Hello!✋ This project is the first team project of the 3rd term of Hyundai IT&E developer training.
We created the shopping mall by referring to the UI of the [Hyundai Food Hall](https://tohome.thehyundai.com/front/dp/dpa/dawnHome.do) website.

# About the project

The “HeendyFoody” web project, a food shopping mall concept, was conducted based on the service analysis provided by “Hyundai Food Hall Two Home”.
HeendyFoody wanted to implement a series of ordering processes in the project where users search for products, put products in their shopping carts to purchase, and check them on My Page.
In addition, the project was carried out by actively utilizing the Java language, database, and Oracle PL/SQL learned while receiving Hyundai IT&E job-specific training.

## What is Heendy?

**‘Heendy’** was created using the initials of Hyundai Department Store’s English initials, H and D, with the motif of a dog, the closest companion to humans. He is a quirky but friendly character who likes to intervene in everything.
We decided on the concept of a food hall introduced by cute Heendy.

## project requirements

**Java**

- IDE는 이클립스, Vs.Code 권장
- servlet JSP JSTL 사용 MVC2 패턴 사용
- Singleton Pattern 적용
- DBCP 적용
- JDBC는 View OR 저장프로시저
- CallableStatement 적극 사용
- 데이터 분석 결과 차트 하나 이상 구현

**When writing code**

- 패키지 이름 변경 [필수]
- 페어 프로그래밍\_pair programming [권장]
- 모든 java 소스에 주석 작성 [필수]
- 소스 작성자 표시 [필수] --> 공동 작성자 표시
- 참조 소스 원본 표시 [권장]

**Database**

- create **new user**
- Table design that does not violate **normalization**
- Using **Views**
- Using **Index**
- Using **Sequence**
- Using **Synonym**
- Using **PL/SQL Package**
- Using **PL/SQL Function**
- Using **PL/SQL Trigger**

# Entity Relationship Diagram

![image](https://user-images.githubusercontent.com/55138034/161561292-963225ed-92e6-4f1b-a95a-17f997e97924.png)

## Project Architecture

![image](https://user-images.githubusercontent.com/55138034/161562017-3033de72-10ac-451d-b0b0-fcb4577ba9f9.png)

**MVC1과 MVC2 패턴 비교**
| |MVC1 |MVC2 |
|----------------|-------------------------------|-----------------------------|
|차이 |JSP가 Controller와 view 모두 담당 |웹 브라우저 요청을 controller에서 처리, model은 결과를 view로 보내어 사용자에게 응답하게 된다. |
|장점 |페이지 흐름이 단순하고 구조가 간단하여 소형 프로젝트에 적합|유지보수 확정에 용이함, controller와 view의 분리로 명료한 구조|
|단점 |유지보수가 어렵다, 규모가 클수록 복잡해진다. |구조 설계를 위한 시간이 많이 소요, 규모가 크고 유지보수가 많은 경우 채택 |

## ⛳ Result

- **Main Page**
  ![image](https://user-images.githubusercontent.com/55138034/161563669-072d2602-5a1c-4b3f-8c12-71a6c9bd945e.png)

- **Login Page**
  ![image](https://user-images.githubusercontent.com/55138034/161563999-0bc74ad7-95f6-4151-9827-0b30b597f3c7.png)

- **Join Page**
  ![image](https://user-images.githubusercontent.com/55138034/161563781-305adf2e-fdd5-4146-9ec9-f1e910b28558.png)

- **Product Page**
  ![image](https://user-images.githubusercontent.com/55138034/161564377-92952f66-5ddb-41db-9163-f5f02a5c36db.png)

- **Product Detail Page**
  ![image](https://user-images.githubusercontent.com/55138034/161564527-4b0a4188-d9f6-4561-8adf-4250b2b3f839.png)

- **Cart Page**
  ![image](https://user-images.githubusercontent.com/55138034/161564735-9fda9db0-7d1c-4830-8e79-be28561259e4.png)

- **My Page**
  ![image](https://user-images.githubusercontent.com/55138034/161564829-9de2926a-d245-4ac5-b081-2190226f0cac.png)

- **My Page (주문 내역, 좋아요, 최근 본 상품)**
  ![image](https://user-images.githubusercontent.com/55138034/161564988-7a3925df-652c-4b62-a9e6-32351aa1a38d.png)

- **Product Management Page**
  ![image](https://user-images.githubusercontent.com/55138034/161565714-5a0349b4-34a6-494c-9144-8d2a90b2b217.png)

- **404 Error Page**
  ![image](https://user-images.githubusercontent.com/55138034/161566176-9c6d0312-6a68-41bc-b6c8-d5c0c00b03b4.png)

## 💻 Presentation

[Presentation_silde](https://docs.google.com/presentation/d/1FHYirrJmvGLEl9P-haPfKdkEQ2sGMjEn/edit?usp=sharing&ouid=103446692371790737362&rtpof=true&sd=true)

## 🔨STACKS & IDE

<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white">
<img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white">
<img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white">
<img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white">
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=Javascript&logoColor=white">
<img src="https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jQuery&logoColor=white">

<img src="https://img.shields.io/badge/Eclipse IDE-2C2255?style=for-the-badge&logo=Eclipse IDE&logoColor=white">
<img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=for-the-badge&logo=Visual Studio Code&logoColor=white">

<img src="https://img.shields.io/badge/Trello-0052CC?style=for-the-badge&logo=Trello&logoColor=white">
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white">
<img src="https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=Discord&logoColor=white">
<img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white">

## 🤝 Contributors - PJT1_TEAM 4

<a href = "https://github.com/sisi397">
  <img src="https://avatars.githubusercontent.com/u/74189924?v=4" alt="sieun" width="80" style="max-width:100%" />
</a>
<a href = "https://github.com/msh1273">
  <img src="https://avatars.githubusercontent.com/u/55138034?v=4" alt="seokho" width="80" style="max-width:100%" />
</a>
<a href = "https://github.com/fmdzimin13">
  <img src="https://avatars.githubusercontent.com/u/77473413?v=4" alt="zimin" width="80" style="max-width:100%" />
</a>
<a href = "https://github.com/tmdwns1101">
  <img src="https://avatars.githubusercontent.com/u/41534475?v=4" alt="seungjun" width="80" style="max-width:100%" />
</a>
