//
//  ExtensionDownloadJSON.swift
//  ShoppingGoodsApp
//  Created by Adam Infiesto on 4/17/19.
//  Copyright © 2019 Adam Infiesto. All rights reserved.
//

import Foundation

//this will extend of whatever controller you tell it too
extension TableViewListItem
{
    
    func downLoadandParse(jsonAtUrl urlString: String)
    {
        //default configs needed for Parsing
        let config = URLSessionConfiguration.default
        
        let session = URLSession(configuration: config)
        
        if let congressURL = URL(string: urlString)
        {
            
            let task = session.dataTask(with: congressURL, completionHandler: { (Data, Response, Error) in
                
                //Bail out on error
                if Error != nil { return }
                
                //Check the response, statusCode, and data
                guard let response = Response as? HTTPURLResponse,
                    response.statusCode == 200,
                    let data = Data
                    else { return }
                
                do {
                    
                    //serialize the data object as string: any cuz it the json opens with {}
                    if let json = try JSONSerialization.jsonObject(with: data, options: .mutableContainers) as? [String: Any]
                    {
                        //parse
                        //the guard will look for json or return nothing
                        //this is good because it will not crash your application
                        guard let result = json["cards"] as? [[String: Any]]
                        else
                        {
                            return
                        }
                        
                        //within the children level
                        //here we will loop through the top layer Json item to get each defined value
                        for member in result
                        {
                            var cardName = member["name"] as? String
                            var cardImage = member["imageUrl"] as? String
                            var cardSubtype = member["subtype"] as? String
                            var cardHealth = member["hp"] as? String
                            
                            //now that we have those card values from the json we need to add/append
                            //to the class object we created in the DataModel
                            //we have an array of object in the table view
                            if(cardName == nil)
                            {
                                cardName = "Asholot"
                            }
                            else if(cardImage == nil )
                            {
                                cardImage = "https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=2ahUKEwjw5JGjhtrhAhWO0J8KHX27BHIQjRx6BAgBEAU&url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DNiEABxi8G4U&psig=AOvVaw1NznCJJxKyE3hXS6ocbRSj&ust=1555690865743429"
                            }
                            else if(cardSubtype == nil)
                            {
                                cardSubtype = "Fire"
                            }
                            else if(cardHealth == nil)
                            {
                                cardHealth = "120"
                            }
                            
                            self.arrayOfCards.append(Card(name: cardName!, url: cardImage!, subtype: cardSubtype!, health: cardHealth!))
                            
                        }
                        
                        //DO NOT DOWNLOAD ON THE MAIN THREAD
                        //Queue to UPDATE UI elements
                        DispatchQueue.main.async
                            {
                                self.tableView.reloadData()
                        }
                    }
                    
                }
                //so if we get an error this will print number code
                //check API documentation if error codes are unknown
                catch
                {
                    print(error.localizedDescription)
                }
            })
            //must resume the task
            task.resume()
        }
    }
    
}

