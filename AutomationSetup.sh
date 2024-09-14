#!/bin/zsh

greenCheck="\u001B[0;32m✔\u001B[0m"
yellowArrow="\u001B[0;33m➜\u001B[0m"
greenUpArrow="\u001B[0;32m▲\u001B[0m"

function version { echo "$@" | awk -F. '{ printf("%d%03d%03d%03d\n", $1,$2,$3,$4); }'; }

echo " "
echo "\u001B[36m====================================================\u001B[0m"
echo "\u001B[34m    __ __    ____                         __   ____"
echo "   /  __   __     __       __       __       __   "
echo "  / / /  / /__   / /___   / /____   / /____   / /__ "
echo " / / /  / / _ \ / / __ \ / / __ \ / / __ \ / / _ \ "
echo " \__/__/ /_/_/_/ /_/  \_\_/ /_/  \_\_/ /_/ \_\____/ "
echo "                   |/        \u001B[0m"
echo "\u001B[36m====================================================\u001B[0m"
echo "\u001B[0;42m\u001B[30m QA Engineer를 위한 환경설정을 진행합니다 \u001B[0m"
echo "\u001B[36m====================================================\u001B[0m"
echo "\u001B[35m☝️ v1.7 written by \u001B[0m"
echo " "

macosVersion=$(sw_vers -productVersion)
macosMinVersion="13.5"
if [ $(version "$macosVersion") -ge $(version "$macosMinVersion") ]; then
  echo "$greenCheck 이미 macOS $macosMinVersion 이상 버전이 설치되어 있습니다! - macOS $macosVersion"
else
  echo "\u001B[0;31m==================================================\u001B[0m"
  echo "macOS $macosMinVersion 이상 버전으로 업데이트가 필요합니다! (현재 버전: $macosVersion)"
  echo "업데이트 후 다시 env-setup.sh를 실행해 주세요!"
  echo "\u001B[0;31m==================================================\u001B[0m"
  exit 0
fi

xcodeVersion=$(xcodebuild -version)
xcodeRequiredVersion="15.4"
if [[ $xcodeVersion == *"$xcodeRequiredVersion"* ]]; then
  echo "$greenCheck 이미 Xcode $xcodeRequiredVersion이 설치되어 있습니다! - $(xcode-select -p)"
else
  echo "$yellowArrow Xcode 설치를 확인합니다"
  echo "\u001B[0;31m==================================================\u001B[0m"
  echo "Xcode $xcodeRequiredVersion 설치가 필요합니다!"
  echo "맞는 버전의 Xcode를 설치하셨다면, 다음 커맨드를 수행해주세요:"
  echo "> sudo xcode-select --switch /Applications/XCode/$xcodeRequiredVersion.app/Contents/Developer"
  echo "> sudo xcodebuild -license accept"
  echo "이후 다시 env-setup.sh를 실행해 주세요!"
  echo "\u001B[0;31m==================================================\u001B[0m"
  exit 0
fi

if ! command -v brew &> /dev/null; then
  echo "$yellowArrow HomeBrew를 설치합니다"
  /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
  if [[ $(sysctl -n machdep.cpu.brand_string) =~ 'Apple' ]]; then
    # Apple Silicon - M1
    echo "eval \"$(/opt/homebrew/bin/brew shellenv)\"" >> ~/.zshrc
  else
    # Intel
    echo "eval \"$(/usr/local/bin/brew shellenv)\"" >> ~/.zshrc
  fi
else
  echo "$greenCheck 이미 HomeBrew가 설치되어 있습니다! - $(which brew)"
fi

if [[ $(sysctl -n machdep.cpu.brand_string) =~ 'Apple' ]]; then
  # Apple Silicon - M1
  eval "$(/opt/homebrew/bin/brew shellenv)"
else
  # Intel
  eval "$(/usr/local/bin/brew shellenv)"
fi

if ! command -v zsh &> /dev/null; then
  echo "$yellowArrow Oh My Zsh를 설치합니다"
  brew install zsh zsh-completions
  sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
  sudo sh -c "echo which zsh >> /etc/shells"
  chsh -s which zsh
  echo "\u001B[0;31m==================================================\u001B[0m"
  echo "zsh를 사용하기 위해 터미널을 재시작해주세요!"
  echo "다시 시작후에 env-setup.sh를 다시 실행해 주세요!"
  echo "\u001B[0;31m==================================================\u001B[0m"
  exit 0
else
  echo "$greenCheck 이미 zsh가 설정되어 있습니다! - $(which zsh)"
fi

if ! command -v git-lfs &> /dev/null; then
  echo "$yellowArrow git-lfs를 설치합니다"
  brew install git-lfs
else
  echo "$greenCheck 이미 git-lfs가 설치되어 있습니다! - $(which git-lfs)"
fi

if ! command -v jq &> /dev/null; then
  echo "$yellowArrow jq를 설치합니다"
  brew install jq
else
  echo "$greenCheck 이미 jq가 설치되어 있습니다! - $(which jq)"
fi

if [[ $(defaults read com.apple.finder AppleShowAllFiles) == "YES" ]]; then
  echo "$greenCheck 이미 Finder에서 숨김 파일 모두 보이기 설정이 되어있습니다!"
else
  echo "$yellowArrow Finder에서 숨김 파일 모두 보기를 설정합니다."
  defaults write com.apple.finder AppleShowAllFiles YES && killall Finder
fi

if [[ -d "/Library/Java/JavaVirtualMachines/jdk-17.0.2.jdk" ]]; then
  echo "$greenCheck 이미 JDK 17.0.2+8가 설치되어 있습니다!"
else
  echo "$yellowArrow JDK 17.0.2+8를 설치합니다"
  cd /Library/Java/JavaVirtualMachines
  sudo curl -o openjdk-17.0.2_macos-x64_bin.tar.gz -L https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_macos-x64_bin.tar.gz
  sudo tar xzf ./openjdk-17.0.2_macos-x64_bin.tar.gz
fi

if [[ -z $JAVA_HOME ]] || [[ $JAVA_HOME != *"jdk-17.0.2.jdk"* ]];  then
  echo "$yellowArrow .zshrc에 JAVA_HOME을 설정합니다"
  sed -i '' '/JAVA_HOME/d' ~/.zshrc
  echo "export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.0.2.jdk/Contents/Home" >> ~/.zshrc
  echo "export PATH=\$PATH:\$JAVA_HOME/bin" >> ~/.zshrc
else
  echo "$greenCheck 이미 JAVA_HOME은 설정되어 있습니다! - $JAVA_HOME"
fi

if [[ -z $ANDROID_HOME ]]; then
  echo "$yellowArrow .zshrc에 ANDROID_HOME을 설정합니다"
  if [[ $(sysctl -n machdep.cpu.brand_string) =~ 'Apple' ]]; then
    # Apple Silicon - M1
    echo "export ANDROID_HOME=/opt/homebrew/Caskroom/android-sdk/4333796" >> ~/.zshrc
  else
    # Intel
    echo "export ANDROID_HOME=/usr/local/share/android-sdk" >> ~/.zshrc
  fi
  echo "export ANDROID_SDK_ROOT=\$ANDROID_HOME" >> ~/.zshrc
  echo "export PATH=\$PATH:\$ANDROID_HOME/emulator:\$ANDROID_HOME/tools:\$ANDROID_HOME/platform-tools:\$ANDROID_SDK_ROOT:\$ANDROID_HOME/cmdline-tools/latest/bin/" >> ~/.zshrc
else
  echo "$greenCheck 이미 ANDROID_HOME은 설정되어 있습니다! - $ANDROID_HOME"
fi

source ~/.zshrc

if [[ -z $ANDROID_HOME ]]; then
  echo "\u001B[0;31m==================================================\u001B[0m"
  echo ".zshrc를 적용하기 위해 터미널을 재시작해주세요!"
  echo "다시 시작 후에 env-setup.sh를 다시 실행해 주세요!"
  echo "\u001B[0;31m==================================================\u001B[0m"
  exit 0
fi

if ! command -v "$ANDROID_HOME"/cmdline-tools/latest/bin/sdkmanager &> /dev/null; then
  echo "$yellowArrow Android SDK를 설치합니다"
  brew install --cask homebrew/cask-versions/temurin8 android-sdk
  mkdir -p "$ANDROID_HOME"/cmdline-tools && cd "$_"
  # https://developer.android.com/studio#downloads:~:text=Command%20line%20tools%20only
  curl -o commandlinetools-mac-9123335_latest.zip -L https://dl.google.com/android/repository/commandlinetools-mac-9123335_latest.zip
  unzip commandlinetools-mac-9123335_latest.zip && mv "cmdline-tools" "latest"
else
  echo "$greenCheck 이미 Android SDK가 설치되어 있습니다!"
fi

echo "$greenUpArrow Android SDK를 업데이트 합니다!"
"$ANDROID_HOME"/cmdline-tools/latest/bin/sdkmanager --licenses
"$ANDROID_HOME"/cmdline-tools/latest/bin/sdkmanager "tools" "platform-tools" "emulator" "build-tools;30.0.3" "platforms;android-33" "extras;android;m2repository"
if [[ $(sysctl -n machdep.cpu.brand_string) =~ 'Apple' ]]; then
  # Apple Silicon - M1
  "$ANDROID_HOME"/cmdline-tools/latest/bin/sdkmanager "system-images;android-33;google_apis_playstore;arm64-v8a"
else
  # Intel
  "$ANDROID_HOME"/cmdline-tools/latest/bin/sdkmanager "system-images;android-33;google_apis_playstore;x86_64" "extras;intel;Hardware_Accelerated_Execution_Manager"
fi
"$ANDROID_HOME"/cmdline-tools/latest/bin/sdkmanager --update

emulators=$("$ANDROID_HOME"/emulator/emulator -list-avds 2>&1)
if [[ $emulators =~ "Pixel_4_API_33" ]]; then
  echo "$greenCheck 이미 테스트용 Android Emulator가 설치되어 있습니다! - Pixel_4_API_33"
else
  echo "$yellowArrow Pixel_4_API_33: 테스트용 Android Emulator를 설정합니다"
  if [[ $(sysctl -n machdep.cpu.brand_string) =~ 'Apple' ]]; then
    # Apple Silicon - M1
    "$ANDROID_HOME"/cmdline-tools/latest/bin/avdmanager -s create avd -f -n Pixel_4_API_33 -b google_apis_playstore/arm64-v8a -k 'system-images;android-33;google_apis_playstore;arm64-v8a' -d 'pixel_4'
  else
    # Intel
    "$ANDROID_HOME"/cmdline-tools/latest/bin/avdmanager -s create avd -f -n Pixel_4_API_33 -b google_apis_playstore/x86_64 -k 'system-images;android-33;google_apis_playstore;x86_64' -d 'pixel_4'
  fi
