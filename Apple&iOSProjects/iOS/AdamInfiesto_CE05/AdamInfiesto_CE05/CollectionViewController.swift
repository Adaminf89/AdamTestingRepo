//
//  CollectionViewController.swift
//  AdamInfiesto_CE05
//
//  Created by Adam Infiesto on 10/16/17.
//  Copyright © 2017 Adam Infiesto. All rights reserved.
//

import UIKit

private var reuseIdentifier = "cell_ID"
private var IdTop = "headID"

class CollectionViewController: UICollectionViewController {

    var personas : [Persona] = [Persona]()
    var charz : [Characterz] = [Characterz]()
    var sectionS : Int!
    var place : IndexPath = []
    var Dis1  = """
Little is known about Naruto's birth or parentage, and it is very probable that he has no living relatives at all. What is known, however, is that around the time his mother was about to give birth to him, the Nine-tails Demon Fox, or Kyūbi no Yōko, attacked his village, Konohagakure (Hidden Leaf) and the Yondaime Hokage (Fourth Hokage) decided to seal the demon away with a forbidden technique inside him, sacrificing his life in the process. Yondaime's wish was for Naruto to be remembered as a hero by the people of the village.
"""
    var Dis2 = """
Sasori (サソリ, Sasori), renowned as Sasori of the Red Sand (赤砂のサソリ, Akasuna no Sasori), was an S-rank missing-nin from Sunagakure's Puppet Brigade and a member of Akatsuki, where he was partnered with Orochimaru and later Deidara.
"""
    var Dis3 = """
Hanzō (半蔵, Hanzō), feared as Hanzō of the Salamander (山椒魚の半蔵, Sanshōuo no Hanzō), was a legendary shinobi, and the leader of Amegakure during his lifetime.
"""
    var Dis4 = """
Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki, Sasuke starts developing his skills. Dissatisfied with his progress, he defects from Konoha so that he can acquire the strength needed to exact his revenge. His years of seeking vengeance and his actions that followed become increasingly demanding, irrational and isolates him from others, leading him to be branded as an international criminal.
"""
    var Dis5 = """
Boruto Uzumaki (うずまきボルト, Uzumaki Boruto) is a shinobi from Konohagakure's Uzumaki clan and a direct descendant of the Hyūga clan through his mother. Initially nonchalant in his duties as a member of Team Konohamaru and is resentful of his father and the office of Hokage because it left him with no time for his family; Boruto eventually comes to respect and reconcile with his father and his role as Hokage, yet vows to become like his mentor Sasuke Uchiha — a support system for the Hokage and the village.
"""
    
    override func viewDidLoad() {
        super.viewDidLoad()

        setterUpper()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


    // MARK: UICollectionViewDataSource

    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return personas.count
    }


    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of items
        //count will change so use persona getter
        return personas[section].getCount()
    }

    
    override func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView
    {
        
        if kind == UICollectionElementKindSectionHeader
        {
            let header = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: IdTop, for: indexPath) as! TopReusableView
            //sectionS = indexPath.section
            //header.viewCollection = self
            //labels for each
            switch indexPath.section
            {
            case 0:
                header.delete.tag = indexPath.section.hashValue
                //self.sectionS = indexPath.section
                header.labelTop.text = "Naruto"
            case 1:
                header.delete.tag = indexPath.section.hashValue
                //self.sectionS = indexPath.section
                header.labelTop.text = "Persona"
                //sectionS = indexPath.section
            case 2:
               header.delete.tag = indexPath.section.hashValue
               //self.sectionS = indexPath.section
               header.labelTop.text = "Memes"
               //sectionS = indexPath.section
            default:
                assertionFailure()
            }
            
            return header
            
        }
        return UICollectionReusableView()
    }
    
    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell
    {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as!CollectionViewCell
        
        //use pathsection and row to pop personas
        let total = personas[indexPath.section].getterSpot(index: indexPath.row)
        
        //make a var using its tag then cast it as a uibutton
        let backBtn = cell.viewWithTag(1) as! UIButton
        
        //configuration
        cell.txtLable.text = total.Name
        cell.imageView.image = total.image
        cell.collectionDesc.text = total.Descriptions
        //hide the button till the view is touched
        backBtn.isHidden = true
        
        
        return cell
    }
    
    override func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath)
    {
        let cell = collectionView.cellForItem(at: indexPath)
        
        cell?.superview?.bringSubview(toFront: cell!)
         
        UIView.animate(withDuration: 0.5, delay: 0, usingSpringWithDamping: 1, initialSpringVelocity: 1, options: [], animations:({
            
            cell?.frame = collectionView.bounds
            //collectionView.isScrollEnabled = false
            
            let backBtn = cell?.viewWithTag(1) as! UIButton
            backBtn.isHidden = false
            
            }), completion: nil)
        
    }

    //links character traits to the types of personas
    func makeCharacter(daClass: Persona, charName:[String], charImage:[UIImage], charDesc: [String] )
    {
        //add the anime traits to the persona class
        for i in 0..<charName.count
        {
            let char = Characterz(Name: charName[i], Descriptions: charDesc[i], image: charImage[i])
        
            daClass.addPersona(daPersona: char)
        }
    }
    
    
    func maker(daName: String) -> Persona
    {
        return Persona(name: daName)
    }
    
    //run at the start
    func setterUpper()
    {
        //make 3 types for each section
        let naruto = maker(daName: "Naruto")
        let pGame = maker(daName: "Persona")
        let memes = maker(daName: "Memes")
        
        //add the types to the persona class
        personas += [naruto,pGame,memes]
        
        //add text and images for each section
        makeCharacter(daClass: naruto, charName: ["Salamanha","Sasori","Naruto","Sasuke","Boruto"], charImage: [#imageLiteral(resourceName: "Hanzō"),#imageLiteral(resourceName: "Sasori"),#imageLiteral(resourceName: "naruto"),#imageLiteral(resourceName: "Sasuke"),#imageLiteral(resourceName: "Boruto")], charDesc: [Dis3,Dis2,Dis1,Dis4,Dis5])
        makeCharacter(daClass: pGame, charName: ["Teddie","Elizabeth","Queen","Luna", "Mo"], charImage: [#imageLiteral(resourceName: "bear"),#imageLiteral(resourceName: "black"),#imageLiteral(resourceName: "Queen"),#imageLiteral(resourceName: "white"),#imageLiteral(resourceName: "flame")],  charDesc: ["Persona 4","Persona 4","Persona 5","Persona 3","Persona Ultimax"])
        makeCharacter(daClass: memes, charName: ["sam","una","Can","Pizza","daDude"], charImage: [#imageLiteral(resourceName: "startrek"),#imageLiteral(resourceName: "crossfit"),#imageLiteral(resourceName: "momspaghetti"),#imageLiteral(resourceName: "storm"),#imageLiteral(resourceName: "wife")], charDesc: ["Star Trek","Who Knows","M&M","StarWar","Friday"])
    }
    
    func delSections(Sections: Int)
    {
        personas.remove(at: Sections)
        self.collectionView?.reloadData()
    }
    
    @IBAction func deleteItems(_ sender: UIButton)
    {
        
        let sections = sender.tag
        
        if sections == 0
        {
            personas.remove(at: sections)
    
           // self.collectionView?.deleteSections(NSIndexSet(index: sections) as IndexSet)
        
            self.collectionView?.reloadData()
        }
        else if sections == 1
        {
            personas.remove(at: sections)
            
            //self.collectionView?.deleteSections(NSIndexSet(index: sections) as IndexSet)
            
            self.collectionView?.reloadData()
        }
        else if sections == 2
        {
            personas.remove(at: sections)
            
           // self.collectionView?.deleteSections(NSIndexSet(index: sections) as IndexSet)
            
            self.collectionView?.reloadData()
        }
        else
        {
            personas.remove(at: sections)
            
            //self.collectionView?.deleteSections(NSIndexSet(index: sections) as IndexSet)
            
            self.collectionView?.reloadData()
        }
        
        
    }
    
   @IBAction func backBTn(_ sender: Any)
   {
        let indexPath = collectionView?.indexPathsForSelectedItems!
        collectionView?.reloadItems(at: indexPath!)
    }
    
    
}
