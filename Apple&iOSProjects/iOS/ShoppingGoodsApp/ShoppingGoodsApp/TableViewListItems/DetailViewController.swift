//
//  ViewController.swift
//  ShoppingGoodsApp
//
//  Created by Adam Infiesto on 4/17/19.
//  Copyright © 2019 Adam Infiesto. All rights reserved.
//

import UIKit
import CoreData

class DetailViewController: UIViewController
{

    @IBOutlet weak var detailImageView: UIImageView!
    
    //coredata stuff
    private var entityDescription : NSEntityDescription!
    private var manageContext : NSManagedObjectContext!
    
    //the card that the user wants to add into coredata
    var userCard : NSManagedObject!
    
    //obj of data passed from table view
    var cardObject : Card!
    
    //
    var userTemp = [Deck]()
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        //download the passed image
        detailImageView.downloadImage(from: cardObject.url)
        
    }
    
    override func viewWillAppear(_ animated: Bool)
    {
        
    }
    
    
    //saveing the card
    @IBAction func saveButton(_ sender: Any)
    {
        //we need to save the card obj into core data we have
        let appDel = UIApplication.shared.delegate as! AppDelegate
        manageContext = appDel.persistentContainer.viewContext
        entityDescription = NSEntityDescription.entity(forEntityName: "UserDeck", in: manageContext)
        
        //setting the cardinto coreData
        userCard.setValue(cardObject.name, forKey: "cName")
        userCard.setValue(cardObject.url, forKey: "cImage")
        userCard.setValue(cardObject.subtype, forKey: "cType")
        userCard.setValue(cardObject.health, forKey: "cHP")
        userCard.setValue(true, forKey: "cOwned")
        
        appDel.saveContext()
        
    }
    
    func getCoreDataArray ()
    {
        let appDel = UIApplication.shared.delegate as! AppDelegate
        manageContext = appDel.persistentContainer.viewContext
        
        entityDescription = NSEntityDescription.entity(forEntityName: "UserDeck", in: manageContext)
        
        userTemp = [NSManagedObject(entity: entityDescription, insertInto: manageContext) as! Deck]
        
        let fetchRequest = NSFetchRequest <NSManagedObject> (entityName: "UserDeck")
        
        do
        {
            
            userTemp = try manageContext.fetch(fetchRequest) as! [Deck]

            for r in userTemp
            {
                print("\(r.description)")
                
            }
            
        }
        catch let error
        {
            print("\(error).")
        }
    }
    
}

