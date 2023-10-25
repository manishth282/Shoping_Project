import '../style/error.css'
const Error=()=>{
    return(
        // <div className="error">
        //     <div className="errorbox">
        //         <div id="code"><h1>404</h1></div><br />
        //         <div id="message">OPPS! PAGE NOT FOUND</div><br />
        //         <div id="addedmessage">Sorry, the page you're looking for doesn't exit.</div>
        //     </div>
        // </div>
        <div className="not-found-container">
        <h1>404</h1>
        <p>Page not found</p>
        <p>Sorry, the page you are looking for does not exist.</p>
        <a href="/">Go back to the homepage</a>
      </div>
    )
}
export default Error;