//
//  TableViewController.swift
//  InfiestoAdam_CE5
//
//  Created by Adam Infiesto on 9/18/17.
//  Copyright © 2017 Adam Infiesto. All rights reserved.
//

import UIKit

class TableViewController: UITableViewController {
    
    var congressDem = [Congress]()
    var congressRep = [Congress]()
    var congressInt = [Congress]()
    
    var filteredPosts = [[Congress](), [Congress](), [Congress]()]
    
    override func viewDidLoad()
    {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
        downParse(jsonAtUrl:"https://congress.api.sunlightfoundation.com/legislators?per_page=all&apikey=1f4393dfee044bb18bb580ef0beb9437")
        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
    }

    override func didReceiveMemoryWarning()
    {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 3
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        
        return filteredPosts[section].count
    }
    
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "reuseID", for: indexPath) as? CongressTableViewCell
            
            let currentpost = filteredPosts[indexPath.section][indexPath.row]
            cell?.daName.text = currentpost.name
            cell?.daParty.text = currentpost.party
            cell?.daTitle.text = currentpost.title
        
        if filteredPosts[indexPath.section][indexPath.row].party.contains("R")
        {
            cell?.backgroundColor = UIColor.red
        }
        else if filteredPosts[indexPath.section][indexPath.row].party.contains("D")
        {
            cell?.backgroundColor = UIColor.blue
        }
        else if filteredPosts[indexPath.section][indexPath.row].party.contains("I")
        {
            cell?.backgroundColor = UIColor.yellow
        }
        
        return cell!
    }
    
    override func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat
    {
        return 50
    }
    

    override func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        
        // section name
        switch section {
        case 0:
            return "Democrates"
        case 1:
            return "Republican"
        case 2:
            return "Independent"
        default:
            return "error"
        }
    }

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?)
    {
        
        if let indexPath = tableView.indexPathForSelectedRow
        {
            
            let dataSent = filteredPosts[indexPath.section][indexPath.row]
            if let destination = segue.destination as? DetailViewController
            {
                destination.member = dataSent
            }
        }
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    

}
