//
//  extensionTableView.swift
//  InfiestoAdam_CE5
//
//  Created by Adam Infiesto on 9/18/17.
//  Copyright © 2017 Adam Infiesto. All rights reserved.
//

import Foundation

extension TableViewController
{
    
    func downParse(jsonAtUrl urlString: String)
    {
        
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
                    if let json = try JSONSerialization.jsonObject(with: data, options: .mutableContainers) as? [String: Any] {
                        //parse
                        guard let result = json["results"] as? [[String: Any]]
                            
                        else
                        {
                            return
                        }
                        //within the children level
                        for member in result
                        {
                            let id = member["bioguide_id"] as? String
                            let fname = member["first_name"] as? String
                            let lname = member["last_name"] as? String
                            let state = member["state_name"] as? String
                            let title = member["title"] as? String
                            let party = member["party"] as? String
                           
                            if (party?.contains("D"))!
                            {
                                self.congressDem.append(Congress(id: id!, state: state!, title: title!, party: party!, name: "\(fname!) \(lname!)"))
                            }
                            else if (party?.contains("R"))!
                            {
                                self.congressRep.append(Congress(id: id!, state: state!, title: title!, party: party!, name: "\(fname!) \(lname!)"))
                            }
                            else if (party?.contains("I"))!
                            {
                                self.congressInt.append(Congress(id: id!, state: state!, title: title!, party: party!, name: "\(fname!) \(lname!)"))
                            }
                            
                            self.filteredPosts = [self.congressDem, self.congressRep, self.congressInt]
                        }
                      
                        //DO NOT DOWNLOAD ON THE MAIN THREAD
                        //Queue to UPDATE UI elements
                        DispatchQueue.main.async
                            {
                                self.tableView.reloadData()
                        }
                    }
                    
                }
                catch {
                    print(error.localizedDescription)
                }
            })
            //must resuem
            task.resume()
        }
    }

}
