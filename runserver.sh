# Appium 서버 실행 함수
start_appium_server() {
    echo "Appium 서버를 시작합니다..."
    appium --address 0.0.0.0 --port 4723 > appium.log 2>&1 &
    sleep 10  # 서버 시작 대기 시간

    # Appium 서버 상태 확인
    if curl -s http://localhost:4723/status > /dev/null; then
        echo "Appium 서버가 성공적으로 실행되었습니다."
    else
        echo "Appium 서버 시작에 실패했습니다."
        cat appium.log
        exit 1
    fi
}

# Appium 서버 실행
start_appium_server

# 서버 실행 후 메시지 출력
echo "Appium 서버가 백그라운드에서 실행 중입니다."
echo "서버를 종료하려면 'pkill -f appium' 명령어를 사용하세요."