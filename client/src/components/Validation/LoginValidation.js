// email 정규식, password 정규식

export default function useLoginValid(form, setIsValid) {
  const emailRegex =
    /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
  // 비밀번호 8자 이상, 최소한 하나의 문자열, 최소한 하나의 숫자
  const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;

  const emailIsValid = form.email && emailRegex.test(form.email);
  const passwordIsValid = form.password && passwordRegex.test(form.password);

  setIsValid({
    email: emailIsValid,
    password: passwordIsValid,
  });
}
