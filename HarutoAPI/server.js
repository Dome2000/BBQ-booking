var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mysql = require('mysql');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));

app.get('/', function(req,res){
    return res.send({error:true, message:'Test huruto API'})
});

var dbConn = mysql.createConnection({
    host: 'localhost',
    user:'root',
    password:'',
    database:'haruto'
});

dbConn.connect();

//registerCus
app.post('/cus', function (req, res) {
  
    var cus = req.body
      
    if (!cus ) {
        return res.status(400).send({ error:true, message: 'Please provide customer ' });
    }

    dbConn.query("INSERT INTO customer SET ? ", cus , function (error, results, fields) { 
        if (error) throw error;
        return res.send(results);
    });

});

//loginCus
app.get('/cus/:customer_username/:customer_password', function(req,res){
    let username = req.params.customer_username
    let password = req.params.customer_password
    if(!username && !password){
        return res.status(400).send({error: true,message:'Please provide user or password'});        
    }
    dbConn.query('SELECT * FROM customer WHERE customer_username = ? AND customer_password = ?',[username, password],function(error, results, fields){
        if(error) throw error;
        if(results[0]){
            return res.send(results[0]);
        }else{
            return res.status(400).send({error: true,message:'Not found!!'});
        }
    });    
})

//allemp 
app.get('/allemp', function(req,res){
    dbConn.query('SELECT * FROM employee', function(error,results, fields){
        if(error) throw error;
        return res.send(results);
    });
});

//loginemp
app.get('/emp/:employee_username/:employee_password', function(req,res){
    let username = req.params.employee_username
    let password = req.params.employee_password
    if(!username && !password){
        return res.status(400).send({error: true,message:'Please provide user or password'});        
    }
    dbConn.query('SELECT * FROM employee WHERE employee_username = ? AND employee_password = ?',[username, password],function(error, results, fields){
        if(error) throw error;
        if(results[0]){
            return res.send(results[0]);
        }else{
            return res.status(400).send({error: true,message:'Not found!!'});
        }
    });    
})

//reserve
app.post('/reserve', function (req, res) {
  
    var cus = req.body      
    if (!cus ) {
        return res.status(400).send({ error:true, message: 'Please provide reserve' });
    }
    dbConn.query("INSERT INTO reserve SET ? ", cus , function (error, results, fields) { 
        if (error) throw error;
        return res.send(results);
    });
    
});

//showReserve
app.get('/allreserve/:customer_username', function(req,res){
    let username = req.params.customer_username
    dbConn.query('SELECT * FROM `reserve` WHERE `customer_username` = ? AND `status_id` <= 3',username, function(error,results, fields){
        if(error) throw error;
        return res.send(results);
    });
});


//deleteReserve
app.delete('/reserve/:reserve_id', function(req, res){
    let id = req.params.reserve_id
    if(!id){
        return res.status(400).send({error: true, message: 'Please provide reserve id'})
    }
    dbConn.query('DELETE FROM reserve WHERE reserve_id = ?', id, function(error, results, fields){
        if(error)throw error
        return res.send({error: false, message: 'reserve has been deleted successfully'})
    })
})


//updateReserve
app.put('/reserve/:reserve_id', function(req, res){
    let reserve_id = req.params.reserve_id
    let reserve = req.body
    if(!reserve_id || !reserve){
        return res.status(400).send({error: true, message: 'Please provide reserve id and reserve data'})
    }
    dbConn.query('UPDATE reserve SET ? WHERE reserve_id = ?', [reserve, reserve_id], function(error, results, fields){
        if(error)throw error
        return res.send({error: false, message: 'reserve has been updated successfully'})
    })
})

//UpdatePassword
app.put('/cus/:customer_username/:customer_password', function(req, res){
    let customer_username = req.params.customer_username
    let customer_password = req.params.customer_password
    let customer = req.body
    if(!customer_username || !customer){
        return res.status(400).send({error: true, message: 'Please provide customer id and reserve data'})
    }
    dbConn.query('UPDATE customer SET ? WHERE customer_username = ? AND customer_password = ?', [customer, customer_username,customer_password], function(error, results, fields){
        if(error)throw error
        return res.send({error: false, message: 'customer has been updated successfully'})
    })
})

//ForgotPassword
app.put('/cus/:customer_username/:customer_email', function(req, res){
    let customer_username = req.params.customer_username
    let customer_email = req.params.customer_email
    let customer = req.body
    if(!customer_username || !customer_email){
        return res.status(400).send({error: true, message: 'Please provide customer id and customer data'})
    }
    dbConn.query('UPDATE customer SET ? WHERE customer_username = ? AND customer_email = ?', [customer, customer_username,customer_email], function(error, results, fields){
        if(error)throw error
        return res.send({error: false, message: 'customer has been updated successfully'})
    })
})
//updateInfo
app.put('/cus/:customer_username', function(req, res){
    let customer_username = req.params.customer_username
    let customer = req.body
    if(!customer_username){
        return res.status(400).send({error: true, message: 'Please provide customer id and customer data'})
    }
    dbConn.query('UPDATE customer SET ? WHERE customer_username = ?', [customer, customer_username], function(error, results, fields){
        if(error)throw error
        return res.send({error: false, message: 'customer has been updated successfully'})
    })
})

//ทั้งหมด
app.get('/reserve', function(req,res){
    let reserve_id = req.params.reserve_id
    dbConn.query('SELECT * FROM `reserve`', function(error,results, fields){
        if(error) throw error;
        return res.send(results);
    });
});

//ค้นหา
app.get('/reserve/:reserve_id', function(req,res){
    let reserve_id = req.params.reserve_id
    dbConn.query('SELECT * FROM `reserve` WHERE `reserve_id` = ?',reserve_id, function(error,results, fields){
        if(error) throw error;
        return res.send(results);
    });
});


//set port
app.listen(3000, function(){
    console.log('Node app is running on port 3000');
});

module.exports = app;