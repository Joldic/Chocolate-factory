import { useState } from "react";
import './styles/AddNewForm.css';

export default function Login(){

  const[username, setUserName] = useState('');
  const[password, setPassword] = useState('');
  const[token, setToken] = useState([])

  const handleSubmit = (e) =>{
    e.preventDefault()
    let user = {username, password}
    console.log(user)
    fetch("http://localhost:8081/auth/login", {
      headers : { 
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },
      method:"POST",
      body:JSON.stringify(user),
    }).then(res => res.json()).then((result)=>
    {
      setToken(result);

      let testToken = {accessToken : "", expiresIn : 0}

      testToken.accessToken = result.accessToken;
      testToken.expiresIn = result.expiresIn;

      localStorage.setItem('testToken', JSON.stringify(testToken));
      localStorage.setItem('user_userName', username);

      console.log(JSON.parse(localStorage.getItem('testToken')));



      if (username === "ivana1234") {
        window.location.href='/SupplyManagerHome';
      }
      else if(username === "cone123"){
          window.location.href='/ProductionManagerHome';

      }
      else if(username === "marko123") {
          window.location.href='/Orders';
      }else if(username === "sesa123"){
          window.location.href='/DeliveryManagerHome';
      }else if(username === "markoMarkovic123" || username === "milanMilic123"){
          window.location.href = "/DriverHomePage"
      }
      else{
        window.location.href='/ProductionManagerHome';
      }
    }
    )
  };

  return(
    <body>
      <div className="wrapper">
        <form onSubmit={handleSubmit}>
          <h1>Chocolate Factory</h1>
          <fieldset>
                <label>
                    <p>Username</p>
                    <input id="userName" name="userName" onChange={(e)=>setUserName(e.target.value)}/>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    <p>Password</p>
                    <input type="password" id="password" name="password" onChange={(e)=>setPassword(e.target.value)}/>
                </label>
            </fieldset>
            <button type="submit">Login</button>
        </form>
      </div>
      <div className="wrapper">
          Create an account? <a href="/UserRegistration">Sign Up</a>
      </div>
      <div className="bodyImg"></div>
      <div className="wrapper">@Chocolate Factory Novi Sad since 2000</div>
    </body>
  );

}