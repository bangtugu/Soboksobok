FROM python:3.10.2

WORKDIR /usr/src/app
# WORKDIR은 cd와 같은 명령으로, 작업 경로를 /usr/src/app으로 이동합니다.
# CMD에서 설정한 실행 파일이 실행될 디렉터리를 지정해주어야 한다.

COPY . .

RUN apt-get update \
  && apt-get install -y 
RUN pip install -r requirements.txt
RUN apt-get install default-jdk -y

WORKDIR ./soboksobok_data
# manage.py를 실행할 수 있는 디렉토리로 이동합니다.

# CMD ["python", "manage.py", "migrate"]

CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]
# 이동한 디렉토리에서 django를 가동시켜주는 코드를 작성합니다. 여기서 port는 8000로 실행시키겠습니다.

EXPOSE 8000