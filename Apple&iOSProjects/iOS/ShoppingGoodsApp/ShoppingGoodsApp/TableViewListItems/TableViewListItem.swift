//
//  TableViewListItem.swift
//  ShoppingGoodsApp
//  Created by Adam Infiesto on 4/17/19.
//  Copyright © 2019 Adam Infiesto. All rights reserved.
//

import UIKit

class TableViewListItem: UITableViewController, UISearchResultsUpdating, UISearchBarDelegate
{
    //NOTE: let varibles cannot be changed while vars can
    //array of items loaded from api
    var arrayOfCards = [Card]()
    
    //this will be the array of sorted items
    var arrayOfNewData = [Card]()
    
    //declare search controllers above
    var SearchController = UISearchController(searchResultsController:nil)
    
    //todo look into making this tableview loading faster
    override func viewDidLoad()
    {
        super.viewDidLoad()
        //set up you top bar items and then
        //call your DownloadJSON extension to start DL data
        topbarStuff()
        downLoadandParse(jsonAtUrl: "https://api.pokemontcg.io/v1/cards")
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }
    
    //this is a funtion that comes with delegates above
    //it cannot be put within the searchbar extension as of swift 5
    func updateSearchResults(for searchController: UISearchController)
    {
        filterContentForSearchText(searchController.searchBar.text!)
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int
    {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        // #warning Incomplete implementation, return the number of rows
        //this will populate rows in the tableview based off of the number of items you pulled from the JSON
        //And added to the array
        //if this is filtering we will use the original arrayOfCards
        //if it isnt we can use the sorted newData array
        if isFiltering()
        {
            return arrayOfNewData.count
        }
        return arrayOfCards.count
    }

   
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        //you're casting this tableview cells as your create type of cell
        let cell = tableView.dequeueReusableCell(withIdentifier: "CellID", for: indexPath) as? ListItemCell
        
        //define the current object to use for tableView
        let currentObjectOfCards : Card!
        
        //if this is filtering we will use the original arrayOfCards
        //if it isnt we can use the sorted newData array
        if isFiltering()
        {
            currentObjectOfCards = arrayOfNewData[indexPath.row]
        }
        else
        {
            currentObjectOfCards = arrayOfCards[indexPath.row]
        }
    
        //here we will use the extension URL ImageConverter
        //to download the image from the passed in url
        cell?.cardImage.downloadImage(from: currentObjectOfCards.url)
        cell?.cardName.text = currentObjectOfCards.name
        cell?.cardType.text = currentObjectOfCards.subtype

        return cell!
    }
 
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?)
    {
        /* show detail about each Card */
        if let indexPath = tableView.indexPathForSelectedRow
        {
            //this is the selected row index
            let selectedRow = indexPath.row
            
            //these are the detail items in each cell
            let cellDetails = segue.destination as! DetailViewController
            
            //we need to know which array we are using because the user could tap when they are searching for cards
            if isFiltering()
            {
                cellDetails.cardObject = arrayOfNewData[selectedRow]
            }
            else
            {
                cellDetails.cardObject = arrayOfCards[selectedRow]
            }
            
        }
    }

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
