
db.getCollection('offer2').find({'status':'ACTIVE','price.currency': 'UAH'}).forEach(
    function(doc){
        db.getCollection('offer2').update({_id: doc._id},
            {$set:
                {
                    'price.baseAmount': parseFloat(doc.price.amount) * 1.0,
                    'price.baseCurrency':'UAH',
                    'price.last_modified_date': new Date()
                }
            }
        );
    })