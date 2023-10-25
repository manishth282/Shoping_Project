let fetchData =async ()=>{
    let data = await fetch('https://jsonplaceholder.typicode.com/posts');
    let res = await data.json();
    console.log(res);
}
fetchData();