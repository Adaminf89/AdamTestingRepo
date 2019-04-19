//
//  Congress.swift
//  InfiestoAdam_CE5
//
//  Created by Adam Infiesto on 9/17/17.
//  Copyright © 2017 Adam Infiesto. All rights reserved.
//

import Foundation
import UIKit

class Congress
{
    //full name, bioguide_id, state, title and party for ALL active legislators.
    var ID : String
    var State : String
    var title : String
    var party : String
    var name : String
    var pic : UIImage?
    
    init(id : String, state : String, title: String, party : String, name: String)
    {
        self.ID = id
        self.State = state
        self.title = title
        self.party = party
        self.name = name
        
        if let memberUrl2 = URL(string:"https://theunitedstates.io/images/congress/225x275/\(id).jpg")
        {
            //create the urls session
            let session2 = URLSession(configuration: URLSessionConfiguration.default)
            
            let task = session2.dataTask(with: memberUrl2, completionHandler: { (data, response, error) in
                
                if error != nil{return}
                
                //check for response
                guard let response = response as? HTTPURLResponse,
                    response.statusCode == 200,
                    
                    let data = data
                    
                    else {return}
                
                if let daPicture = UIImage(data: data)
                {
                    self.pic = daPicture
                } else {
                    self.pic = nil
                }

                //self.pic = UIImage(data: data)
                
            })
            
            task.resume()
        }

    }
    
    
    
    
}
